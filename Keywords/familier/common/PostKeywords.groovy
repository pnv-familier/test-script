package familier.common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

public class PostKeywords {

	@Keyword
	def createPostDynamic(String content) {
		Mobile.comment('Step: Checking for Create buttons')
		
		TestObject btnFloatingAdd = findTestObject('Object Repository/FamilySocial/CreateMorePost/btn_CreateMorePost')
		TestObject btnCreateFirst = findTestObject('Object Repository/FamilySocial/CreatePost/btn_CreatePost')

		boolean isCreatedFromPlus = false

		if (Mobile.verifyElementVisible(btnFloatingAdd, 5, FailureHandling.OPTIONAL)) {
			Mobile.comment('Found Floating Add (+) button. Clicking to create post.')
			Mobile.tap(btnFloatingAdd, 10)
			isCreatedFromPlus = true 
		}

		else {
			Mobile.comment('Floating (+) not found. Checking for "Create First Post" button...')
			if (Mobile.verifyElementVisible(btnCreateFirst, 5, FailureHandling.OPTIONAL)) {
				Mobile.tap(btnCreateFirst, 10)
				isCreatedFromPlus = false 
			} else {
				Mobile.comment('ERROR: No create button found on screen!')
				return false
			}
		}

		Mobile.setText(findTestObject('Object Repository/FamilySocial/CreatePost_Common/txt_PostContent'), content, 10)
		Mobile.tap(findTestObject('Object Repository/FamilySocial/CreatePost_Common/btn_SubmitPost'), 10)
		Mobile.delay(3)
		
		return isCreatedFromPlus
	}

	@Keyword
	def reactToLatestPost() {
		Mobile.comment('Step: Reacting Love to the latest post')
		TestObject btnLove = findTestObject('Object Repository/FamilySocial/React/btn_LoveReaction')
		Mobile.waitForElementPresent(btnLove, 10)
		Mobile.tap(btnLove, 10)
		Mobile.delay(1)
	}

	@Keyword
	def commentOnLatestPost(String message) {
		Mobile.comment("Step: Tapping Comment Icon")
		Mobile.tap(findTestObject('Object Repository/FamilySocial/Comment/btn_CommentIcon'), 10)
		
		TestObject txtInput = findTestObject('Object Repository/FamilySocial/Comment/txt_CommentInput')
		Mobile.waitForElementPresent(txtInput, 10)
		
		Mobile.comment("Step: Typing message: ${message}")
		Mobile.sendKeys(txtInput, message)
		
		// ĐỢI 2 GIÂY để nút Send từ trạng thái Disabled chuyển sang Enabled
		Mobile.delay(2)

		Mobile.comment("Step: Hiding Keyboard to reveal the Send button")
		// Một số máy ảo bàn phím che mất nút Send, phải ẩn đi mới bấm được
		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		Mobile.delay(5)

		Mobile.comment("Step: Finalizing Send Action")
		TestObject btnSend = findTestObject('Object Repository/FamilySocial/Comment/btn_SendComment')
		
		// Chờ nút Send xuất hiện (dùng content-desc sẽ rất nhanh)
		Mobile.waitForElementPresent(btnSend, 10)
		
		// Bấm nút Send
		Mobile.tap(btnSend, 10)
		
		// Đợi 3 giây để comment kịp bay lên server
		Mobile.delay(3)
		Mobile.comment("Success: Comment flow completed.")
	}
	
	@Keyword
	def deleteAllPosts() {
		Mobile.comment("--- BẮT ĐẦU QUY TRÌNH XÓA TẤT CẢ BÀI VIẾT ---")
		
		TestObject btnOptions = findTestObject('Object Repository/FamilySocial/DeletePost/btn_PostOptions')
		TestObject btnDeleteMenu = findTestObject('Object Repository/FamilySocial/DeletePost/btn_Delete')
		TestObject btnConfirmDelete = findTestObject('Object Repository/FamilySocial/DeletePost/btn_ConfirmDelete')

		boolean postsExist = true
		int deleteCount = 0

		while (postsExist) {
			postsExist = Mobile.verifyElementVisible(btnOptions, 5, FailureHandling.OPTIONAL)

			if (postsExist) {
				deleteCount++
				Mobile.comment("Đang xóa bài viết thứ: " + deleteCount)
				
				// 1. Bấm nút ba chấm
				Mobile.tap(btnOptions, 10)
				
				// 2. Bấm chọn 'Delete' từ Menu hiện lên
				Mobile.waitForElementPresent(btnDeleteMenu, 5)
				Mobile.tap(btnDeleteMenu, 10)
				
				// 3. Bấm 'DELETE' trên Popup xác nhận (XPath android:id/button1)
				Mobile.waitForElementPresent(btnConfirmDelete, 5)
				Mobile.tap(btnConfirmDelete, 10)
				
				Mobile.comment("Đã xóa xong bài thứ " + deleteCount + ". Đang đợi Feed cập nhật...")
				
				// Đợi một chút để App xóa xong và load lại danh sách trước khi tìm bài tiếp theo
				Mobile.delay(3)
			} else {
				Mobile.comment("Không còn bài viết nào để xóa.")
			}
			
			// Giới hạn an toàn để tránh vòng lặp vô tận (ví dụ tối đa xóa 20 bài)
			if (deleteCount >= 20) {
				Mobile.comment("Đã đạt giới hạn xóa 20 bài. Dừng vòng lặp để an toàn.")
				break
			}
		}
		
		Mobile.comment("--- HOÀN THÀNH XÓA TỔNG CỘNG: " + deleteCount + " BÀI ---")
	}
}