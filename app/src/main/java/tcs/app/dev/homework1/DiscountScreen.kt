package tcs.app.dev.homework1

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material.icons.outlined.Euro
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tcs.app.dev.R
import tcs.app.dev.homework1.data.Cart
import tcs.app.dev.homework1.data.Discount
import tcs.app.dev.homework1.data.plus


@Composable
fun DiscountScreen(
    discount: List<Discount>,
    cart: Cart,
    updateCart: (Cart) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(discount) { discount ->
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Column (
                        modifier = Modifier.fillMaxWidth(0.5f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start){
                        Row (modifier = Modifier.fillMaxWidth(0.8f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center){
                            if (discount is Discount.Fixed){
                                val amount = discount.amount.toString()
                                Icon(Icons.Outlined.Euro, contentDescription = null, modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(40.dp))
                                Text(stringResource(R.string.amount_off,amount), color = MaterialTheme.colorScheme.onPrimary)

                            }
                            else if (discount is Discount.Percentage){
                                val value = discount.value
                                Icon(Icons.Outlined.Percent, contentDescription = null, modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(40.dp))
                                Text(stringResource(R.string.percentage_off,value), color = MaterialTheme.colorScheme.onPrimary)
                            }
                            else if (discount is Discount.Bundle){
                                val amountGet = discount.amountItemsGet
                                val amountPay = discount.amountItemsPay
                                val item = discount.item.id
                                Icon(Icons.Outlined.Discount, contentDescription = null, modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(40.dp))
                                Text(stringResource(R.string.pay_n_items_and_get, amountPay, item, amountGet), color = MaterialTheme.colorScheme.onPrimary)

                            }
                        }
                    }

                    Column (
                        modifier = Modifier.fillMaxWidth(0.8f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = { updateCart(cart.plus(discount))},
                            enabled = !cart.discounts.contains(discount),
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
