package testng1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestNgExercises {

    @DataProvider(name = "additionValues")
    public Object[][] additionValues() {
        return new Object[][]{{5, 5, 10}, {6, 10, 16}};
    }

    @Test(dataProvider = "additionValues", groups = {"addition"})
    public void testAdditionProvider(int a, int b, int expectedResult) {
        int actualResult = a + b;

        Assert.assertEquals(expectedResult, actualResult);

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }

    @DataProvider(name = "subtractValues")
    public Object[][] subtractValues() {
        return new Object[][]{{5, 5, 0}, {6, 3, 3}, {20, 9, 11}};
    }

    @Test(dataProvider = "subtractValues", groups = {"subtract"})
    public void testSubtractValuesProvider(int a, int b, int expectedResult) {
        int actualResult = a - b;

        Assert.assertEquals(expectedResult, actualResult);

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }

    @DataProvider(name = "multiplyValues")
    public Object[][] multiplyValues() {
        return new Object[][]{{2, 4, 8}, {8, 8, 64}, {7, 7, 49}};
    }

    @Test(dataProvider = "multiplyValues", groups = {"multiply"})
    public void testMultiplyValuesProvider(int a, int b, int expectedResult) {
        int actualResult = a * b;

        Assert.assertEquals(actualResult, expectedResult);

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }

    @DataProvider(name = "divideValues")
    public Object[][] divideValues() {
        return new Object[][]{{6, 3, 2}, {50, 5, 10}, {8, 2, 4}};
    }

    @Test(dataProvider = "divideValues", groups = {"divide"})
    public void testDivideValuesProvider(int a, int b, int expectedResult) {
        int actualResult = a / b;

        Assert.assertEquals(actualResult, expectedResult);

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }

    @DataProvider(name = "moduleDivideValues")
    public Object[][] moduleDivideValues() {
        return new Object[][]{{6, 3, 0}, {10, 2, 0}};
    }
    @Test(dataProvider = "moduleDivideValues", groups = {"module"})
    public void testModuleDivideValueProvider(int a, int b, int expectedResult) {
        int actualResult = a % b;

        Assert.assertEquals(actualResult, expectedResult);

        long id = Thread.currentThread().threadId();
        System.out.println(id);
    }
}