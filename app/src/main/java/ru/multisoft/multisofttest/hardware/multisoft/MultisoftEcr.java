package ru.multisoft.multisofttest.hardware.multisoft;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.multisoft.drivers.FiscalCoreDriver;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import ru.multisoft.multisofttest.R;
import ru.multisoft.multisofttest.constants.Alignment;
import ru.multisoft.multisofttest.constants.Wrap;
import ru.multisoft.multisofttest.hardware.EcrDriver;
import ru.multisoft.multisofttest.hardware.EcrStatus;
import ru.multisoft.multisofttest.hardware.EcrStatusType;
import ru.multisoft.multisofttest.hardware.TextPrint;
import ru.multisoft.multisofttest.helpers.DateUtils;
import ru.multisoft.multisofttest.helpers.MathUtils;
import ru.multisoft.multisofttest.helpers.TextUtils;
import ru.multisoft.multisofttest.model.OrderItem;
import ru.multisoft.multisofttest.model.PaymentType;
import ru.multisoft.multisofttest.model.Product;

public class MultisoftEcr extends EcrDriver {

    public static final String DRIVER_VERSION_NAME = "1.3.0";
    public static final String TEMP_DIR = "/Multisoft/MSPOS/";
    public static final int DEFAULT_LINE_LENGTH = 45;

    private static EcrDriver sInstance;

    private FiscalCoreDriver mDriver;
    private Context mContext;
    private final String mSettings;

    private int mLineLength = 40;

    private MultisoftEcr(@NonNull Context context, String settings) {
        mContext = context.getApplicationContext();
        mSettings = settings;
        setStatus(new EcrStatus(EcrStatusType.NOT_INITIALIZED, "mContext.getString(R.string.ecr_not_initialized)"));
    }

    public static EcrDriver init(Context context, String settings) {
        if (sInstance == null) {
            sInstance = new MultisoftEcr(context, settings);
        }
        return sInstance;
    }

    private void initSettings() throws IOException {
        Settings settingsData = getDefaultSettings();
        mLineLength = settingsData.getLineLength();
    }

    @Override
    public boolean prepare() {
        int statusType = EcrStatusType.OK;
        String message = "mContext.getString(R.string.ecr_init_success)";

        if (mDriver == null) {
            try {
                mDriver = new FiscalCoreDriver();
                mDriver.Initialize(mContext.getResources().openRawResource(R.raw.config_core),
                        new File(Environment.getExternalStorageDirectory() + TEMP_DIR),
                        "");
                initSettings();
                if (!mDriver.SelfTest()) {
                    mDriver = null;
                    statusType = EcrStatusType.NOT_INITIALIZED;
                    message = "mContext.getString(R.string.ecr_init_failure)" + ": self test failed.";
                }
            } catch (Exception e) {
                mDriver = null;
                statusType = EcrStatusType.NOT_INITIALIZED;
                message = "mContext.getString(R.string.ecr_init_failure)" + ":" + e.toString();
            }
        }

        setStatus(new EcrStatus(statusType, message));
        return statusType == EcrStatusType.OK;
    }

    @Override
    public boolean finish() {
        if (mDriver != null) {
            mDriver.UnInitialize();
        }
        setStatus(new EcrStatus(EcrStatusType.OK, "mContext.getString(R.string.ecr_work_finished)"));
        return true;
    }

    @Override
    public EcrStatus getStatus() {
        return super.getStatus();
    }

    @Override
    public boolean payment(List<OrderItem> items, List<Product> products, PaymentType payType) {
        boolean isOK = true;

        cancelCheck();

        BigDecimal total = BigDecimal.ZERO;

        if (mDriver.OpenDoc(FiscalCoreDriver.DOC_TYPE_SELL)) {
            for (int i = 0; i < items.size(); i++) {
                OrderItem orderItem = items.get(i);
                if (MathUtils.isPositive(orderItem.getQuantity())) {
                    String title = products.get(i).getName();
                    BigDecimal quantity = orderItem.getQuantity();
                    BigDecimal price = orderItem.getPrice();
                    if (!registration(title, price, quantity)) {
                        isOK = false;
                        break;
                    }
                    total = total.add(quantity.multiply(price));
                }
            }
        } else {
            isOK = false;
        }

        if (isOK && payment(total, payType.getCounterNumber()) && closeCheck()) {
            setEcrStatusOK("mContext.getString(R.string.ecr_payment_success)");
        } else {
            isOK = false;
            setEcrStatusDriverError();
            cancelCheck();
        }

        return isOK;
    }

    @Override
    public String reportX() {
        if (mDriver.PrintXReport()) {
            setEcrStatusOK("mContext.getString(R.string.ecr_report_success)");
            return String.valueOf(DateUtils.getDateSeconds());
        } else {
            setEcrStatusDriverError();
            return null;
        }
    }

    @Override
    public String reportZ() {
        if (mDriver.PrintZReport()) {
            setEcrStatusOK("mContext.getString(R.string.ecr_report_success)");
            return String.valueOf(DateUtils.getDateSeconds());
        } else {
            setEcrStatusDriverError();
            return null;
        }
    }

    @Override
    public boolean cashIncome(BigDecimal amount) {
        return cashOperation(FiscalCoreDriver.DOC_TYPE_PAID_IN, amount);
    }

    @Override
    public boolean cashOutcome(BigDecimal amount) {
        return cashOperation(FiscalCoreDriver.DOC_TYPE_PAID_OUT, amount);
    }

    private boolean cashOperation(int operationType, BigDecimal amount) {
        cancelCheck();

        boolean isOK = mDriver.OpenDoc(operationType)
                && mDriver.PrintRecItem(amount)
                && payment(amount, FiscalCoreDriver.PAY_TYPE_CASH)
                && mDriver.CloseDoc();

        if (isOK) {
            setEcrStatusOK("mContext.getString(R.string.ecr_cash_income_success)");
        } else {
            setEcrStatusDriverError();
            cancelCheck();
        }

        return isOK;
    }

    @Override
    public boolean setDemoMode(boolean isDemo) {
        return super.setDemoMode(isDemo);
    }

    private boolean registrationRefund(String title, BigDecimal price, BigDecimal quantity) {
        return mDriver.PrintRecItem(quantity, price, title);
    }

    private void setEcrStatusOK(String message) {
        setStatus(new EcrStatus(EcrStatusType.OK, message));
    }

    private void setEcrStatusDriverError() {
        setStatus(new EcrStatus(EcrStatusType.DRIVER_ERROR, getDriverStatus()));
    }

    private String getDriverStatus() {
        return String.valueOf(mDriver.getLastError());
    }

    private boolean closeCheck() {
        return mDriver.CloseDoc();
    }

    private boolean payment(BigDecimal total, int counterNumber) {
        return mDriver.PrintRecTotal(total) && mDriver.PrintRecItemPay(counterNumber, total);
    }

    private boolean registration(String title, BigDecimal price, BigDecimal quantity) {
        return mDriver.PrintRecItem(quantity, price, title);
    }

    private void cancelCheck() {
        if (mDriver.getDocState() != FiscalCoreDriver.DOC_STATE_CLOSED) {
            mDriver.CancelDoc();
        }
    }

    @Override
    public String getInfo() {
        return super.getInfo();
    }

    @Override
    public BigDecimal cashSumm() {
        return super.cashSumm();
    }

    @Override
    public boolean printString(List<TextPrint> commands) {
        for (TextPrint command : commands) {
            int align = convertAlign(command.getAlignment());
            String[] printText = wrapText(command);
            for (String atomPrintText : printText) {
                if (!mDriver.PrintLine(align, atomPrintText)) {
                    setEcrStatusDriverError();
                    return false;
                }
            }
        }

        setEcrStatusOK("mContext.getString(R.string.ecr_print_success)");
        return true;
    }

    private String[] wrapText(TextPrint command) {
        String result;

        switch (command.getWrap()) {
            case Wrap.WRAP_LINE:
                result = TextUtils.wrap(command.getText(), mLineLength, null, true);
                break;
            case Wrap.WRAP_WORD:
                result = TextUtils.wrap(command.getText(), mLineLength, null, false);
                break;
            default:
                result = command.getText();
                break;
        }

        return result.split(TextUtils.LINE_SEPARATOR);
    }

    private int convertAlign(int alignment) {
        switch (alignment) {
            case Alignment.ALIGNMENT_CENTER:
                return FiscalCoreDriver.ALIGN_CENTER;
            case Alignment.ALIGNMENT_RIGHT:
                return FiscalCoreDriver.ALIGN_RIGHT;
            default:
                return FiscalCoreDriver.ALIGN_LEFT;
        }
    }

    @Override
    public boolean cut() {
        return mDriver.Print("{cut}");
    }

    @Override
    public boolean feed(int lines) {
        for (int i = 0; i < lines; i++) {
            if (!mDriver.Print("{lf}")) {
                return false;
            }
        }
        return true;
    }

    public static Settings getDefaultSettings() {
        return new Settings().setLineLength(DEFAULT_LINE_LENGTH);
    }

    @Override
    public String getVersionName() {
        return DRIVER_VERSION_NAME;
    }
}
