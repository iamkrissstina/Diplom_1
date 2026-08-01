import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestBurger {
//Назначение булочки
    @Test
    public void setBunTest() {
        Burger burger = new Burger();
        Bun expectedBun = new Bun("black bun", 100);
        burger.setBuns(expectedBun);
        assertEquals(burger.bun, expectedBun);
    }
// Добавление 1 ингредиента
    @Test
    public void addOneIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        assertTrue("Список ингредиентов должен быть пустым до добавления", burger.ingredients.isEmpty());
        burger.addIngredient(ingredient);
        assertEquals("Проверяем, что добавился 1 ингредиент", 1, burger.ingredients.size());
        Ingredient firstIngredient = burger.ingredients.get(0);
        assertSame("Проверяем, что добавился нужный ингредиент", ingredient, firstIngredient);
    }
    // добавление 2 ингредиентов
    @Test
    public void addSomeIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        assertTrue("Список ингредиентов должен быть пустым до добавления", burger.ingredients.isEmpty());
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals("Проверяем, что добавилось всего 2 ингредиента", 2, burger.ingredients.size());
        assertSame("Проверяем, что добавился первый ингредиент", ingredient1, burger.ingredients.get(0));
        assertSame("Проверяем, что добавился второй ингредиент", ingredient2, burger.ingredients.get(1));
    }
    //Удаление ингредиента
    @Test
    public void removeIngredientTest() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(ingredient1,ingredient2));
        Burger burger = new Burger();
        burger.ingredients = listIngredient;
        assertEquals("До удаления должно быть 2 ингредиента", 2, burger.ingredients.size());
        burger.removeIngredient(0); //удаляем первый ингредиент
        assertEquals("После удаления должен быть 1 ингредиент", 1, burger.ingredients.size());
        assertFalse("Удалённый ингредиент должен отсутствовать", burger.ingredients.contains(ingredient1));
        assertSame("Второй ингредиент встаёт на место первого", ingredient2, burger.ingredients.get(0));
    }

    //Перемещение ингредиентов
    @Test
    public void moveIngredientTest() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(ingredient1, ingredient2));
        Burger burger = new Burger();
        burger.ingredients = listIngredient;
        burger.moveIngredient(1, 0);
        assertSame(ingredient1, burger.ingredients.get(1));
        assertSame(ingredient2, burger.ingredients.get(0));
    }
@Mock
Bun bunMock;
@Mock
Ingredient ingredientMock;
    // Получить цену
    @Test
    public void getPriceTest_full() {
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(ingredientMock));
        Burger burger = new Burger();
        burger.bun = bunMock;
        burger.ingredients = listIngredient;
        Mockito.when(bunMock.getPrice()).thenReturn(100f);
        Mockito.when(ingredientMock.getPrice()).thenReturn(100f);
        float actualPrice = burger.getPrice();
        assertEquals(300f, actualPrice, 0.001f);
    }
    @Test
    public void getPriceTest_withoutIngredient() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        Mockito.when(bunMock.getPrice()).thenReturn(100f);
        float actualPrice = burger.getPrice();
        assertEquals(200f, actualPrice, 0.001f);
    }

    // Рецепт
    @Test
    public void getReceipt() {
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(ingredientMock));
        Burger burger = new Burger();
        burger.bun = bunMock;
        burger.ingredients = listIngredient;
        Mockito.when(bunMock.getName()).thenReturn("black bun");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientMock.getName()).thenReturn("hot sauce");
        Mockito.when(burger.getPrice()).thenReturn(250f);
        String receipt = burger.getReceipt();
        String normalized = receipt.replace("\r\n", "\n"); // нормализуем переносы, ибо без этого не принимает

        String expected = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "(==== black bun ====)\n\n" +
                String.format("Price: %f\n", 250f);

        assertEquals(expected, normalized);
    }





}
