package ru.multisoft.multisofttest;

import android.content.Context;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.multisoft.multisofttest.constants.Alignment;
import ru.multisoft.multisofttest.hardware.EcrDriver;
import ru.multisoft.multisofttest.hardware.TextPrint;
import ru.multisoft.multisofttest.hardware.multisoft.MultisoftEcr;
import ru.multisoft.multisofttest.model.OrderItem;
import ru.multisoft.multisofttest.model.PaymentType;
import ru.multisoft.multisofttest.model.Product;

public class Test {

    public static final void testPrint(Context context) {
        EcrDriver driver = MultisoftEcr.init(context, null);
        if (driver.prepare()) {
            printMarks(driver);
            payment(driver);
        }
    }

    private static void printMarks(EcrDriver driver) {
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

        driver.printString(printCommands);
        driver.cut();
    }

    private static void payment(EcrDriver driver) {
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

        driver.payment(orderItems, products, paymentType);
    }

}
