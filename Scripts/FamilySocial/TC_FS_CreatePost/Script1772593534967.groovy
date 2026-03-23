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
import io.appium.java_client.android.nativekey.KeyEvent
import io.appium.java_client.android.nativekey.AndroidKey


import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable as GlobalVariable

Mobile.callTestCase(findTestCase('TestCases/Authentication/TC_Login'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.comment('--- BẮT ĐẦU: TẠO BÀI VIẾT ---')

// 1. Đợi màn hình Home load xong (Dùng Profile Icon làm mốc)
Mobile.waitForElementPresent(findTestObject('Object Repository/FamilySocial/Logout/btn_ProfileIcon'), 20)

// 2. Gọi Keyword tạo bài và LƯU TRẠNG THÁI vào Global Variable
// Hàm này sẽ trả về true nếu dùng nút (+) và false nếu dùng nút to
GlobalVariable.isNewPostFromPlus = CustomKeywords.'familier.common.PostKeywords.createPostDynamic'('Automated Post Content từ Katalon!')

// 3. VERIFY: Xác nhận nội dung bài viết đã hiển thị trên Feed
Mobile.comment('Step: Verify post appearance')
Mobile.waitForElementPresent(findTestObject('Object Repository/FamilySocial/CreatePost_Common/lbl_LatestPostContent'), 15)
Mobile.verifyElementExist(findTestObject('Object Repository/FamilySocial/CreatePost_Common/lbl_LatestPostContent'), 10)

Mobile.comment('Success: Post created. Is from Plus: ' + GlobalVariable.isNewPostFromPlus)
