package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class IngredientTests {
    private Ingredient ingredient;
    private IngredientType expectedIngredientType = IngredientType.SAUCE;
    private String expectedName = "TestSauce";
    private float expectedPrice = 20f;
    @Before
    public void setUp() {
        this.ingredient = new Ingredient(
                expectedIngredientType,
                expectedName,
                expectedPrice
        );
    }
    @Test
    public void getPriceIsCorrect() {
        MatcherAssert.assertThat(
                "Некорретная стоимость ингредиента",
                ingredient.getPrice(),
                equalTo(expectedPrice)

        );

    }
    @Test
    public void getNameIsCorrect() {
        MatcherAssert.assertThat(
                "Некорректное наименование ингредиента",
                ingredient.getName(),
                equalTo(expectedName)
        );
    }
    @Test
    public void getTypeIsCorrect() {
        MatcherAssert.assertThat(
                "Некорректный тип ингредиента",
                ingredient.getType(),
                equalTo(expectedIngredientType)
        );
    }
}