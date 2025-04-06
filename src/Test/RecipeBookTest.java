package Test;

import Code.Recipe;
import Code.RecipeBook;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RecipeBook Test Suite")
public class RecipeBookTest {
    private RecipeBook recipeBook;

    @BeforeEach
    void init() {
        recipeBook = new RecipeBook();
    }

    @Test
    @DisplayName("Add and retrieve recipe")
    void testAddRecipe() {
        Recipe r = new Recipe();
        r.setName("Coffee");
        assertTrue(recipeBook.addRecipe(r));
        Recipe[] recipes = recipeBook.getRecipes();
        assertEquals("Coffee", recipes[0].getName());
    }

    @Test
    @DisplayName("Reject duplicate recipe")
    void testDuplicateRecipe() {
        Recipe r = new Recipe();
        r.setName("Tea");
        assertTrue(recipeBook.addRecipe(r));
        assertFalse(recipeBook.addRecipe(r));
    }

    @Test
    @DisplayName("Delete existing recipe")
    void testDeleteRecipe() {
        Recipe r = new Recipe();
        r.setName("Latte");
        recipeBook.addRecipe(r);
        String deleted = recipeBook.deleteRecipe(0);
        assertEquals("Latte", deleted);
    }

    @Test
    @DisplayName("Delete non-existing recipe returns null")
    void testDeleteNull() {
        assertNull(recipeBook.deleteRecipe(2));
    }

    @Test
    @DisplayName("Edit recipe with valid data")
    void testEditRecipe() {
        Recipe r1 = new Recipe();
        r1.setName("Espresso");
        recipeBook.addRecipe(r1);

        Recipe r2 = new Recipe();
        r2.setName("Mocha");
        String oldName = recipeBook.editRecipe(0, r2);
        assertEquals("Espresso", oldName);
    }

    @Test
    @Timeout(1)
    @DisplayName("Performance test for edit")
    void testEditTimeout() {
        Recipe r = new Recipe();
        r.setName("Americano");
        recipeBook.addRecipe(r);
        recipeBook.editRecipe(0, new Recipe());
    }
}
