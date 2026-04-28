package com.example.eventmanagingapp.ui.theme.screens.cart

@Composable
fun CartScreen(navController: NavController) {
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems by viewModel.cartItems.collectAsState()
    val totalAmount by viewModel.totalAmount.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Shopping Cart",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(cartItems) { cartItem ->
            CartItemRow(
                cartItem = cartItem,
                onQuantityChange = { qty -> viewModel.updateQuantity(cartItem.id, qty) },
                onRemove = { viewModel.removeFromCart(cartItem.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total:", style = MaterialTheme.typography.titleLarge)
                        Text("₹$totalAmount", style = MaterialTheme.typography.titleLarge)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.placeOrder() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Place Order")
                    }
                }
            }
        }
    }
}