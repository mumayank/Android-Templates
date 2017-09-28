package mumayank.templaterecyclerview.three_item_types;

/**
 * Created by Mayank on 28-09-2017.
 */

public class MyItem {

    private int myVal;
    private MyEnum myEnum;

    public MyItem(int myVal, MyEnum myEnum) {
        this.myVal = myVal;
        this.myEnum = myEnum;
    }

    public int getMyVal() {
        return myVal;
    }

    public MyEnum getMyEnum() {
        return myEnum;
    }
}
