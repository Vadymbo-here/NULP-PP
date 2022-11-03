package test.banking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banking.Bank;

public class BankTest {

        Bank test = new Bank();

        // Test (getter) and setters.
        // if there is logic
        // 

        @Before
        public void initBank() {
                test.setBankID(12202);
                test.setName("Test");
        }
        
        @Test
        public void testIfBankExists() {
                String[] params;
                test.setBankID(-1);
                // Example: change dep {depID} {key} {newVal}.
                params = "change dep 1 name Tessssst".split(" ");
                Assert.assertEquals("Error occupied!", test.ChangeDepCase(params));
        }

        // NO PARAMS
        @Test
        public void testChangeDepCaseNoParams() {
                String[] params;

                params = "change dep".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ChangeDepCase(params));
        }

        @Test
        public void testCreateDepCaseNoParams() {
                String[] params;

                params = "create dep".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.CreateDepCase(params));
        }

        @Test
        public void testDeleteDepCaseNoParams() {
                String[] params;

                params = "del dep".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.DeleteDepCase(params));
        }

        // WRONG PARAMS
        @Test
        public void testChangeDepCaseWrongParams() {
                String[] params;

                params = "change dep 123".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ChangeDepCase(params));

                params = "change dep 123 123".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ChangeDepCase(params));

                params = "change dep 123 asd".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ChangeDepCase(params));

                params = "change dep asd 123".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ChangeDepCase(params));

                params = "change dep asd asd".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.ChangeDepCase(params));
        }

        @Test
        public void testCreateDepCaseWrongParams() {
                String[] params;

                params = "create dep 123".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.CreateDepCase(params));
        }

        @Test
        public void testDeleteDepCaseWrongParams() {
                String[] params;

                params = "del dep".split(" ");
                Assert.assertEquals(
                                "Wrong Command or params. Try \"Help me\" to see available command with their description.",
                                test.DeleteDepCase(params));
        }

        @Test
        public void testShowDepCases() {
                Assert.assertEquals("Command executed succesfully",
                                test.ShowDepCases());
        }
}
