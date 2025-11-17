package tcs.app.dev.homework1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Shop
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.MockData
import tcs.app.dev.homework1.data.plus

@Composable
fun ShopItemScreen(
    shop: Shop,
    cart: Cart,
    updateCart: (Cart) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = shop.items.toList()
    val prices = shop.prices
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items) { items ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.20f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var id = MockData.getImage(items)
                        Image(
                            painterResource(id),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )

                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.25f)
                            .padding(5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(items.id)
                    }


                    Column(
                        modifier = Modifier.fillMaxWidth(0.4f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    )
                    { Text(prices.getValue(items).toString()) }

                    Column(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { updateCart(cart.plus(items)) },
                            colors = buttonColors(
                                containerColor = MaterialTheme.colorScheme.onSecondary,
                                contentColor = MaterialTheme.colorScheme.secondary,
                                disabledContainerColor =
                                    MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
                                disabledContentColor =
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                            ),
                        ) {
                            Text(stringResource(R.string.description_add_to_cart))
                        }
                    }
                }

            }
        }

    }

}
