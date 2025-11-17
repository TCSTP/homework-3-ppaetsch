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
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Shop
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.Euro
import tcs.app.dev.homework1.data.Item
import tcs.app.dev.homework1.data.MockData
import tcs.app.dev.homework1.data.minus
import tcs.app.dev.homework1.data.plus
import tcs.app.dev.homework1.data.times

@Composable
fun CartScreen(
    shop: Shop, cart: Cart, updateCart: (Cart) -> Unit, modifier: Modifier = Modifier
) {
    val discounts = cart.discounts.toList()
    val items = cart.items.keys.toList()
    val prices = shop.prices
    var total = calculatePrices(cart, shop, items, prices)
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    stringResource(R.string.title_cart),
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

        }
    }, bottomBar = {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    stringResource(R.string.total_price, total),
                    modifier = Modifier.padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { },
                    enabled = cart.itemCount > 0u,
                    colors = buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(
                            alpha = 0.5f
                        ),
                        disabledContentColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
                    ),
                ) {
                    Text(stringResource(R.string.label_pay))
                }
            }

        }
    }) { paddingValues ->

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
                        modifier = Modifier.fillMaxWidth(0.2f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        var single = prices.getValue(items)
                        var amount = cart.items.getValue(items)
                        var price = single * amount
                        total = total + price
                        Text(price.toString())
                    }
                    Row(modifier = Modifier.fillMaxWidth(0.5f)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .size(50.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { updateCart(cart.plus(items)) },
                                colors = buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onSecondary,
                                    contentColor = MaterialTheme.colorScheme.secondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(
                                        alpha = 0.5f
                                    ),
                                    disabledContentColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.5f
                                    )
                                ),
                            ) {
                                Text(stringResource(R.string.description_increase_amount))
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(50.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { updateCart(cart.minus(items)) },
                                colors = buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onSecondary,
                                    contentColor = MaterialTheme.colorScheme.secondary,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(
                                        alpha = 0.5f
                                    ),
                                    disabledContentColor = MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.5f
                                    )
                                ),
                            ) {
                                Text(stringResource(R.string.description_decrease_amount))
                            }
                        }
                    }

                }

            }
            items(discounts) { discount ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.25f)
                            .padding(5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        if (discount is Discount.Fixed) {
                            val amount = discount.amount.toString()
                            Text(
                                stringResource(R.string.amount_off, amount),
                                color = MaterialTheme.colorScheme.primary
                            )

                        } else if (discount is Discount.Percentage) {
                            val value = discount.value
                            Text(
                                stringResource(R.string.percentage_off, value),
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else if (discount is Discount.Bundle) {
                            val amountGet = discount.amountItemsGet
                            val amountPay = discount.amountItemsPay
                            val item = discount.item.id
                            Text(
                                stringResource(
                                    R.string.pay_n_items_and_get,
                                    amountPay,
                                    item,
                                    amountGet
                                ), color = MaterialTheme.colorScheme.primary
                            )

                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { updateCart(cart.minus(discount)) },
                            colors = buttonColors(
                                containerColor = MaterialTheme.colorScheme.onSecondary,
                                contentColor = MaterialTheme.colorScheme.secondary,
                                disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(
                                    alpha = 0.5f
                                ),
                                disabledContentColor = MaterialTheme.colorScheme.secondary.copy(
                                    alpha = 0.5f
                                )
                            ),
                        ) {
                            Text(stringResource(R.string.description_remove_from_cart))
                        }
                    }
                }
            }
        }
    }
}


fun calculatePrices(cart: Cart, shop: Shop, items: List<Item>, prices: Map<Item, Euro>): Euro {
    var total = Euro(0u)
    for (item in items) {
        var single = prices.getValue(item)
        var amount = cart.items.getValue(item)
        var price = single * amount
        total = total + price
    }
    return total
}