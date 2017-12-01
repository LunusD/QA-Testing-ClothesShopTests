import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage {

    @FindBy(css = "#header > div.nav > div > div > nav > div.header_user_info > a")
    WebElement signInButton;

    @FindBy(css = "#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.left-block > div > a.product_img_link > img")
    WebElement product1;

    @FindBy(css = "#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default > span")
    WebElement product1AddToCart;

    @FindBy(css = "")
    WebElement product2;

    public void clickSignIn(){
        signInButton.click();
    }

    public void openProduct1Page(){
        product1.click();
    }

    public void addProduct1ToCart(){
        product1AddToCart.click();
    }

}
