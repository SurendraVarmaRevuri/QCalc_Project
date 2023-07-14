package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";
    // WebDriverWait wait = new WebDriverWait(driver, 20);

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            // Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/"));

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // Thread.sleep(2000);

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            // Thread.sleep(1000);
            WebElement searchBox = driver.findElement(By.xpath(
                    "//div[@class='MuiFormControl-root MuiTextField-root search-desktop css-i44wyl']//input"));
            searchBox.clear();
            searchBox.sendKeys(product);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']")),
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.xpath(" //h4[normalize-space(text())='No products found']"))));

            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {};
        try {
             Thread.sleep(3000);


            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results

            searchResults = driver
                    .findElements(By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']"));
            // WebDriverWait wait = new WebDriverWait(driver,20);
            // wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));


            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false

            WebElement noProductDisplayed =
                    driver.findElement(By.xpath("//h4[text()=' No products found ']"));

            if (noProductDisplayed.isDisplayed()) {
                status = true;
            }

            return status;


        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {

            Thread.sleep(1000);
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding to the
             * matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> productNameValidation = driver.findElements(
                    By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']//p[1]"));
            for (int i = 0; i < productNameValidation.size(); i++) {
                WebElement actualProductName = productNameValidation.get(i);
                String productNameText = actualProductName.getText();
                if (productName.equals(productNameText)) {

                    WebDriverWait wait = new WebDriverWait(driver, 20);

                    wait.until(ExpectedConditions
                            .elementToBeClickable(By.xpath("//button[text()='Add to cart']")));
                    WebElement clickAddtoCart =
                            driver.findElement(By.xpath("//button[text()='Add to cart']"));

                    clickAddtoCart.click();
                    return true;
                }


            }

            System.out.println("Unable to find the given product");
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement clickCheckOut = driver.findElement(
                    By.xpath("//div[@class='cart-footer MuiBox-root css-1bvc4cc']//child::button"));
            clickCheckOut.click();
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName

            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)


            List<WebElement> actualProductName =
                    driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']/div[1]"));

            List<WebElement> productQuantity =
                    driver.findElements(By.xpath("//div[@data-testid='item-qty']"));

            List<WebElement> increment =
                    driver.findElements(By.xpath("//*[@data-testid='AddOutlinedIcon']"));

            List<WebElement> decrement =
                    driver.findElements(By.xpath("//*[@data-testid='RemoveOutlinedIcon']"));

            for (int i = 0; i < actualProductName.size(); i++) {
                WebElement actualProduct = actualProductName.get(i);

                String productNameText = actualProduct.getText();
                if (productName.equals(productNameText)) {

                    while (true) {
                        WebElement actualProductIndex = productQuantity.get(i);
                        String exactActualProductQuantity = actualProductIndex.getText();
                        int actualProuctQuantity = Integer.parseInt(exactActualProductQuantity);
                        WebDriverWait wait = new WebDriverWait(driver, 20);

                        if (quantity > actualProuctQuantity) {// 2 > 1
                            WebElement clickPlusButton = increment.get(i);
                            clickPlusButton.click();
                            wait.until(ExpectedConditions.textToBePresentInElement(
                                    actualProductIndex, String.valueOf(actualProuctQuantity + 1)));



                        } else if (quantity < actualProuctQuantity) { // 0 < 1
                            WebElement clickMinusButton = decrement.get(i);
                            clickMinusButton.click();
                            wait.until(ExpectedConditions.or(
                                    ExpectedConditions.textToBePresentInElement(actualProductIndex,
                                            String.valueOf(actualProuctQuantity - 1)),
                                    ExpectedConditions.invisibilityOf(actualProduct)));

                        } else if (quantity == actualProuctQuantity) {
                            break;
                        }

                    }
                }

            }



            // if (driver.findElement(By.xpath("//div[text()='Xtend Smart Watch']")).getText()
            // .equals(productName)) {
            // WebElement itemQuatity =
            // driver.findElement(By.xpath("//div[@data-testid='item-qty']"));
            // if (itemQuatity.getText().equals("1")) {
            // WebElement increment =
            // driver.findElement(By.xpath("//div[@class='css-u4p24i']//button[2]"));
            // increment.click();

            // } else if (itemQuatity.getText().equals("2")) {
            // WebElement decrese =
            // driver.findElement(By.xpath("//div[@class='css-u4p24i']//button[1]"));
            // decrese.click();
            // }

            // }

            // else if (driver.findElement(By.xpath("//div[text()='Yarine Floor Lamp']")).getText()
            // .equals(productName)) {
            // List<WebElement> itemQuatity =
            // driver.findElements(By.xpath("//div[@data-testid='item-qty']"));
            // for (WebElement qa : itemQuatity) {
            // if (qa.getText().equals("1")) {
            // WebElement decrement = driver.findElement(By.xpath(
            // "//*[@id='root']/div/div/div[3]/div[2]/div/div[2]/div/div[2]/div[2]/div[1]/button[1]"));
            // decrement.click();
            // }
            // }
            // }



            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements
            List<WebElement> items =
                    driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']/div[1]"));

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart
            for (int i = 0; i < expectedCartContents.size(); i++) {
                String cartItemIndex = expectedCartContents.get(i);
                WebElement actualCartItemIndex = items.get(i);
                String actualCartItemText = actualCartItemIndex.getText();

                if (!cartItemIndex.equals(actualCartItemText)) {
                    return false;
                }
                System.out.println("Expected Cart products : " + cartItemIndex + " = "
                        + "Actual Cart Products :" + actualCartItemText);

            }


            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
