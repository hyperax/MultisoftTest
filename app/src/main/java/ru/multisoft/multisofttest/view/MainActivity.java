package ru.multisoft.multisofttest.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.multisoft.multisofttest.R;
import ru.multisoft.multisofttest.constants.Alignment;
import ru.multisoft.multisofttest.hardware.EcrDriver;
import ru.multisoft.multisofttest.hardware.TextPrint;
import ru.multisoft.multisofttest.hardware.multisoft.MultisoftEcr;
import ru.multisoft.multisofttest.model.OrderItem;
import ru.multisoft.multisofttest.model.PaymentType;
import ru.multisoft.multisofttest.model.Product;

public class MainActivity extends AppCompatActivity {

    private EcrDriver mDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTest();
            }
        });
    }

    private void runTest() {
        if (mDriver == null) {
            mDriver = MultisoftEcr.init(this, null);
        }

        if (mDriver.prepare()) {
            reportZ();
            printMarks();
            payment();
            reportZ();
        }
    }

    private void printMarks() {
        List<OrderItem> items = new ArrayList<>();
        List<TextPrint> printCommands = new ArrayList<>(items.size()*2);

        // header
        printCommands.add(new TextPrint("Заказ № 1").setAlignment(Alignment.ALIGNMENT_CENTER));
        printCommands.add(new TextPrint(" "));

        printCommands.add(new TextPrint("Блюдо 1 X 11 "));
        printCommands.add(new TextPrint("Блюдо с длинным наименованием и «Слово в кавычках» X 50"));
        printCommands.add(new TextPrint("Блюдо 1 X 11 "));
        printCommands.add(new TextPrint("Блюдо с длинным наименованием и «Слово в кавычках» X 50"));
        printCommands.add(new TextPrint("Блюдо 1 X 11 "));
        printCommands.add(new TextPrint("Блюдо с длинным наименованием и «Слово в кавычках» X 50"));
        printCommands.add(new TextPrint("Блюдо 1 X 11 "));
        printCommands.add(new TextPrint("Блюдо с длинным наименованием и «Слово в кавычках» X 50"));
        printCommands.add(new TextPrint("Блюдо 1 X 11 "));
        printCommands.add(new TextPrint("Блюдо с длинным наименованием и «Слово в кавычках» X 50"));
        printCommands.add(new TextPrint("Блюдо 1 X 11 "));
        printCommands.add(new TextPrint("Блюдо с длинным наименованием и «Слово в кавычках» X 50"));

        // footer
        printCommands.add(new TextPrint(" "));
        printCommands.add(new TextPrint(" "));
        printCommands.add(new TextPrint(" "));

        mDriver.printString(printCommands);
        mDriver.cut();
    }

    private void payment() {
        List<Product> products = new ArrayList<>();

        products.add(new Product().setName("Блюдо 1"));
        products.add(new Product().setName("Блюдо с длинным наименованием и «Слово в кавычках»"));

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem()
                .setQuantity(new BigDecimal("11"))
                .setPrice(new BigDecimal("123")));

        orderItems.add(new OrderItem()
                .setQuantity(new BigDecimal("50"))
                .setPrice(new BigDecimal("7")));

        PaymentType paymentType = new PaymentType().setCounterNumber(1);

        mDriver.payment(orderItems, products, paymentType);
    }

    private void reportZ() {
        mDriver.reportZ();
    }

}
