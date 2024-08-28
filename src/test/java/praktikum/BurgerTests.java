package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient ingredient2;

    @Test
    public void setBunsIsCorrect() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("TestBun");
        burger.setBuns(bun);
        MatcherAssert.assertThat(
                "Булочки не добавлены",
                bun.getName(),
                equalTo(burger.bun.getName())
        );
    }


    @Test
    public void addIngredientIsCorrect() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertTrue(
                "Ингредиент не добавлен",
                burger.ingredients.contains(ingredient)
        );
    }


    @Test
    public void moveIngredientIsCorrect() {
        Mockito.when(ingredient.getName()).thenReturn("TestIngredient");

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        int currentIndex = burger.ingredients.indexOf(ingredient);
        int newIndex = burger.ingredients.indexOf(ingredient2);

        burger.moveIngredient(currentIndex, newIndex);

        MatcherAssert.assertThat(
                "Ингредиент не перемещается",
                ingredient.getName(),
                equalTo(burger.ingredients.get(newIndex).getName())
        );
    }


    @Test
    public void removeIngredientIsCorrect() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);

        assertFalse(
                "Ингредиент не удаляется",
                burger.ingredients.contains(ingredient)
        );
    }


    @Test
    public void getPriceIsCorrect() {
        Mockito.when(bun.getPrice()).thenReturn(10f);
        Mockito.when(ingredient.getPrice()).thenReturn(20f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        MatcherAssert.assertThat(
                "Цена расчитывается некорректно",
                40f,
                equalTo(burger.getPrice())
        );

    }
    @Test
    public void getReceiptIsCorrect() {
        Mockito.when(bun.getName()).thenReturn("TestBun");
        Mockito.when(bun.getPrice()).thenReturn(20f);
        Mockito.when(ingredient.getName()).thenReturn("TestIngredient");
        Mockito.when(ingredient.getPrice()).thenReturn(10f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        // Используем System.lineSeparator() для создания платформо-независимых строк
        String lineSeparator = System.lineSeparator();
        String expectedReceipt =
                "(==== TestBun ====)" + lineSeparator +
                        "= filling TestIngredient =" + lineSeparator +
                        "(==== TestBun ====)" + lineSeparator +
                        lineSeparator +
                        "Price: 50,000000" + lineSeparator;

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        MatcherAssert.assertThat(
                "Некорректная строка с рецептом",
                burger.getReceipt(),
                equalTo(expectedReceipt)
        );
    }
}