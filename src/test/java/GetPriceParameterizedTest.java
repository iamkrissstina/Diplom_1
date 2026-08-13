
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(Parameterized.class)
public class GetPriceParameterizedTest {
    @Mock
    Bun bunMockParam;

    @Mock
    Ingredient ingredientMockParam;

    private float expectedPrice;
    private boolean hasIngredient;

    public GetPriceParameterizedTest(float expectedPrice, boolean hasIngredient) {
        this.expectedPrice = expectedPrice;
        this.hasIngredient = hasIngredient;
    }

    //Два теста для расчета цены: с ингредиентами и без
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {200f, false},
                {300f, true}
        });
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        burger.bun = bunMockParam;
        when(bunMockParam.getPrice()).thenReturn(100f);
        when(ingredientMockParam.getPrice()).thenReturn(100f);

        burger.setBuns(bunMockParam);
        if (hasIngredient) {
            List<Ingredient> listIngredient = new ArrayList<>(Arrays.asList(ingredientMockParam));
            burger.ingredients = listIngredient;
        }
        float actualPrice = burger.getPrice();
        assertEquals("Цена должна соответствовать ожидаемой", expectedPrice, actualPrice, 0.001f);
    }
}

