package com.dev.roomdatabase.screen.user

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dev.roomdatabase.R
import com.dev.roomdatabase.ui.theme.Gray100

@Composable
fun UserDetailScreen(
    viewModel: UserViewModel,
    context: Context
) {

    val focusManager = LocalFocusManager.current

    val fullName by viewModel.fullName
        .collectAsState()

    val email by viewModel.email
        .collectAsState()

    val address by viewModel.address
        .collectAsState()

    val phone by viewModel.phone
        .collectAsState()

    val isLoading by viewModel.isLoading
        .collectAsState()

    val successText = stringResource(id = R.string.txt_user_saved)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.90f)
                .padding(all = 16.dp),
            elevation = 6.dp,
            backgroundColor = Gray100
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 8.dp)
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.Center,
            ) {

                Image(
                    painterResource(id = R.drawable.signore_orange),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    contentDescription = ""
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    value = fullName,
                    onValueChange = viewModel::onFullNameChanged,
                    label = { Text(text = stringResource(id = R.string.lbl_full_name)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.user_solid),
                            contentDescription = ""
                        )
                    },
                    textStyle = MaterialTheme.typography.subtitle2,
                    isError = (fullName.length <= 3),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    value = email,
                    onValueChange = viewModel::onEmailChanged,
                    label = { Text(text = stringResource(id = R.string.lbl_email)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.envelope_solid),
                            contentDescription = ""
                        )
                    },
                    textStyle = MaterialTheme.typography.subtitle2,
                    isError = (email.length <= 5),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    value = address,
                    onValueChange = viewModel::onAddressChanged,
                    label = { Text(text = stringResource(id = R.string.lbl_address)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.location_pin_solid),
                            contentDescription = ""
                        )
                    },
                    textStyle = MaterialTheme.typography.subtitle2,
                    isError = (address.length <= 5),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    value = phone,
                    onValueChange = viewModel::onPhoneChanged,
                    label = { Text(text = stringResource(id = R.string.lbl_phone)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone, imeAction = ImeAction.Done
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.phone_solid),
                            contentDescription = ""
                        )
                    },
                    textStyle = MaterialTheme.typography.subtitle2,
                    isError = (phone.length <= 5),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            viewModel.addOrUpdateUser{
                                Toast.makeText(context, successText, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                        modifier = Modifier
                            .requiredWidth(width = 160.dp)
                            .requiredHeight(height = 60.dp)
                            .padding(vertical = 10.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
                            else {
                                Text(
                                    text = stringResource(id = R.string.lbl_save),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
