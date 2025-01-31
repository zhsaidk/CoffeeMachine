# CoffeeMachine
Простое веб-приложение на Spring Boot с PostgreSQL

###  Главные вкладки
  GET /coffees – возвращает страницу со списком всех типов кофе.
  GET /coffees/{id} – загружает страницу конкретного кофе с рецептом.
  POST /coffees/{id}/do – запускает приготовление кофе и редиректит на страницу этого кофе.
  IngredientController
  
  GET /ingredients – показывает список всех ингредиентов.
  POST /ingredients/restock – пополняет ингредиенты и редиректит обратно.
