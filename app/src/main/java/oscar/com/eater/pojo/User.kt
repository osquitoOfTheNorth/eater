package oscar.com.eater.pojo

data class User(val email: String, val userName: String, val recipes: List<SavedRecipe>) {
}