package test.userclass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import userclass.User;
import utils.JsonWorker;

public class UserTest {

        User test = new User();

        @Before
        public void initUser() {
                test.setBankID(0);
                test.setUserID(2);
        }

        @Test
        public void testShowDealsNormalInput() {
                String[] params;

                params = "show deals 12000 120".split(" ");
                Assert.assertEquals("Command executed succesfully", test.ShowDeals(params));

                params = "show deals 1000 30".split(" ");
                Assert.assertEquals("Command executed succesfully", test.ShowDeals(params));

                params = "show deals 10000 90".split(" ");
                Assert.assertEquals("Command executed succesfully", test.ShowDeals(params));

                params = "show deals 100000 160".split(" ");
                Assert.assertEquals("Command executed succesfully", test.ShowDeals(params));
        }

        @Test
        public void testShowDealsWrongParams() {
                String[] params;

                params = "show deals".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ShowDeals(params));

                params = "deals".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ShowDeals(params));

                params = "show".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ShowDeals(params));

                params = "show deals 12000".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ShowDeals(params));

                params = "show deals 1".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ShowDeals(params));

                params = "show my deals".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ShowDeals(params));
        }

        @Test
        public void testShowDealsWrongParamsType() {
                String[] params;

                params = "show deals asd asd".split(" ");
                Assert.assertEquals("There is no such element. Check you params.",
                                test.ShowDeals(params));

                // params = "show deals 10000 asd".split(" ");
                Assert.assertEquals("There is no such element. Check you params.",
                                test.ShowDeals(params));

                // params = "show deals asd 90".split(" ");
                Assert.assertEquals("There is no such element. Check you params.",
                                test.ShowDeals(params));
        }

        @Test
        public void testLoginWithTrueParams() {
                test = JsonWorker.loginUser("asd", "asd");
                Assert.assertEquals(2, test.getUserID());
        }

        @Test
        public void testLoginWithFalseParams() {
                test = JsonWorker.loginUser("-1", "!@#");
                Assert.assertEquals(0, test.getUserID());
        }
}
