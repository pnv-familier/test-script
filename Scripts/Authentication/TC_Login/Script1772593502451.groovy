import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling

Mobile.comment("--- BẮT ĐẦU TEST CASE LOGIN ---")


// Kiểm tra: Nếu đã ở màn hình Home (thấy nút Profile) thì không cần Login nữa
if (Mobile.verifyElementVisible(findTestObject('Object Repository/FamilySocial/Logout/btn_ProfileIcon'), 5, FailureHandling.OPTIONAL)) {
	Mobile.comment("Đã đăng nhập sẵn. Bỏ qua các bước nhập liệu.")
} else {
	// Nếu chưa Login thì mới gọi hàm Login
	CustomKeywords.'familier.common.AuthKeywords.loginWithProfile'()
}

// Xác nhận Login thành công
Mobile.verifyElementVisible(findTestObject('Object Repository/FamilySocial/Logout/btn_ProfileIcon'), 10)