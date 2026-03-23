import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration



class FamilierTestListener {

	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		// 1. Chỉ mở App nếu chưa mở
		if (GlobalVariable.isAppStarted == false) {
			String appPath = RunConfiguration.getProjectDir() + "/App/familier.apk"
			Mobile.startApplication(appPath, false)
			GlobalVariable.isAppStarted = true
		}
	
		// 2. CHỈ TỰ ĐỘNG LOGIN NẾU ĐÂY KHÔNG PHẢI LÀ BÀI TC_LOGIN
		// Thay 'TC_Login' bằng tên chính xác của file Test Case đăng nhập của bạn
		if (!testCaseContext.getTestCaseId().contains('TC_Login')) {
			
			Mobile.comment("Checking if auto-login is needed for: " + testCaseContext.getTestCaseId())
			
			// Chờ xem có nút Login không (đợi 5s)
			boolean isAtLogin = Mobile.verifyElementVisible(findTestObject('Object Repository/Login/btn_Login'), 5, FailureHandling.OPTIONAL)
			
			if (isAtLogin) {
				Mobile.comment("Login screen detected. Performing auto-login...")
				CustomKeywords.'familier.common.AuthKeywords.loginWithProfile'()
			}
		}
	}
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		Mobile.comment("--- FINISHED TEST CASE: " + testCaseContext.getTestCaseId() + " ---")
		
	
	}

	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		Mobile.comment("--- FINISHED TEST SUITE. Closing application... ---")
		
		// Chỉ đóng App sau khi toàn bộ Suite (tất cả các bài test) đã hoàn thành
		Mobile.closeApplication()
		GlobalVariable.isAppStarted = false
	}
}