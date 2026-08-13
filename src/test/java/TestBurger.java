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
    @Mock
    Bun bunMock;
    @Mock
    Ingredient firstIngr;
    @Mock
    Ingredient secondIngr;
    //Назначение булочки
    @Test
    public void setBunTest() {
        Burger burger = new Burger();
        Bun expectedBun = bunMock;
        burger.setBuns(expectedBun);
        assertEquals(burger.bun, expectedBun);
    }
// Добавление 1 ингредиента
    @Test
    public void addOneIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngr);
        assertEquals("Проверяем, что добавился 1 ингредиент", 1, burger.ingredients.size());
    }

    // добавление 2 ингредиентов
    @Test
    public void addSomeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngr);
        burger.addIngredient(secondIngr);
        assertEquals("Проверяем, что добавилось всего 2 ингредиента", 2, burger.ingredients.size());

    }
    //Удаление ингредиента
    @Test
    public void removeIngredientTest() {
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(firstIngr, secondIngr));
        Burger burger = new Burger();
        burger.ingredients = listIngredient;
        burger.removeIngredient(0); //удаляем первый ингредиент
        assertEquals("После удаления должен быть 1 ингредиент", 1, burger.ingredients.size());
    }

    //Перемещение ингредиентов
    @Test
    public void moveIngredientTest() {
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(firstIngr, secondIngr));
        Burger burger = new Burger();
        burger.ingredients = listIngredient;
        burger.moveIngredient(1, 0);
        assertSame(firstIngr, burger.ingredients.get(1));
    }


    // Рецепт
    @Test
    public void getReceipt() {
        List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(firstIngr));
        Burger burger = new Burger();
        burger.bun = bunMock;
        burger.ingredients = listIngredient;
        Mockito.when(bunMock.getName()).thenReturn("black bun");
        Mockito.when(firstIngr.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(firstIngr.getName()).thenReturn("hot sauce");
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
