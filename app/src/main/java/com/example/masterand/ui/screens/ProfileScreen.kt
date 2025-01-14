package com.example.masterand.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.masterand.ui.components.OutlinedTextFieldWithError
import com.example.masterand.ui.components.ProfileImageWithPicker
import com.example.masterand.utils.AnimationAndTransitionUtils
import com.example.masterand.utils.Validators
import com.example.masterand.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navigateToGameScreen: (colorCount: Int) -> Unit,
//    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()
) {
    // Coroutine scope for suspend functions
    val coroutineScope = rememberCoroutineScope()

    // State variables
    val name = rememberSaveable { viewModel.name }
    val email = rememberSaveable { viewModel.email }
    val colorCount = rememberSaveable { mutableStateOf("6") }
    val profileImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    // Image picker launcher
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedUri ->
            if (selectedUri != null) {
                profileImageUri.value = selectedUri
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Game Title
        AnimationAndTransitionUtils.AnimateScale { scale ->
            Text(
                text = "MasterAnd",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = TransformOrigin.Center
                    }
                    .padding(bottom = 48.dp)
            )
        }

        // Profile Image Picker
        ProfileImageWithPicker(
            profileImageUri = profileImageUri.value,
            selectImageOnClick = {
                imagePicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Name TextField
        OutlinedTextFieldWithError(
            value = name,
            label = "Name",
            validator = { Validators.validateName(it) },
            errorMessage = "Name can't be empty",
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email TextField
        OutlinedTextFieldWithError(
            value = email,
            label = "Email",
            validator = { Validators.validateEmail(it) },
            errorMessage = "Invalid email address",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Color Count TextField
        OutlinedTextFieldWithError(
            value = colorCount,
            label = "Number of Colors",
            validator = {
                try {
                    Validators.validateColorCount(it.toIntOrNull() ?: 0)
                } catch (e: NumberFormatException) {
                    false
                }
            },
            errorMessage = "Color count must be between 5 and 10",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Continue Button
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.savePlayer()
                }
                navigateToGameScreen(colorCount.value.toInt())
            },
            enabled = Validators.validateName(name.value) &&
                    Validators.validateEmail(email.value) &&
                    Validators.validateColorCount(colorCount.value.toIntOrNull() ?: 0)
        ) {
            Text("Continue")
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(
//        navController
        {}
    )
}