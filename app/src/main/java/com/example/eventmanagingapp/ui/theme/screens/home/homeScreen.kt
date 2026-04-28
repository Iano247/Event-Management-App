package com.example.eventmanagingapp.ui.theme.screens.home

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val materials by viewModel.materials.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Event Materials",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search materials...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        items(materials) { material ->
            MaterialCard(
                material = material,
                onAddToCart = { viewModel.addToCart(material) },
                onSupplierClick = {
                    navController.navigate("supplier/${material.supplierId}")
                }
            )
        }
    }
}

@Composable
fun MaterialCard(
    material: Material,
    onAddToCart: () -> Unit,
    onSupplierClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = material.imageUrl,
                contentDescription = material.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = material.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = material.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "₹${material.price}/ ${material.unit}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Supplier: ${material.supplierId}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Column {
                IconButton(onClick = onAddToCart) {
                    Icon(
                        Icons.Default.AddShoppingCart,
                        contentDescription = "Add to cart",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                TextButton(onClick = onSupplierClick) {
                    Text("View Supplier")
                }
            }
        }
    }
}