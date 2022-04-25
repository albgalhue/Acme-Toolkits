package acme.testing.inventor.tool;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorToolListShowTest extends TestHarness{
	// Test cases ---------------------------------------------------
	
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/tool/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String type, final String name, 
			final String code, final String technology, final String description, final String retailPrice, final String link) {
			super.signIn("inventor2", "inventor2");
			
			super.clickOnMenu("Inventor", "ToolList");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, type);
			super.checkColumnHasValue(recordIndex, 1, name);
			super.checkColumnHasValue(recordIndex, 2, code);
			super.checkColumnHasValue(recordIndex, 3, technology);
			super.checkColumnHasValue(recordIndex, 4, description);
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("item-type", type);
			super.checkInputBoxHasValue("name", name);
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("technology", technology);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("retailPrice", retailPrice);
			super.checkInputBoxHasValue("link", link);
			
			super.signOut();
			
		}
}
