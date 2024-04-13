package duke.deviluke.mangadexapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import duke.deviluke.mangadexapp.BuildConfig
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.R
import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.databinding.FragmentLoginBinding
import duke.deviluke.mangadexapp.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        binding.btnLogin.setOnClickListener(login)
        (activity as MainActivity).binding.bnvMain.visibility = View.GONE
    }

    private val login = View.OnClickListener {
        val username = binding.edAccount.text.toString()
        val password = binding.edPassword.text.toString()
        if (username.isNotBlank() && password.isNotBlank()) {
            val authData = AuthData(
                grantType = "password",
                username = binding.edAccount.text.toString(),
                password = binding.edPassword.text.toString(),
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET
            )
            lifecycleScope.launch {
                getAuthToken(authData)
            }
        } else {
            Snackbar.make(
                requireContext(),
                binding.root,
                "Username or password is empty. Please enter!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private suspend fun getAuthToken(authData: AuthData) {
        Log.d(DEBUG_TAG, "LoginFragment: getAuthToken()")
        viewModel.getAuthToken(authData)
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.authToken.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        Log.d(DEBUG_TAG, "LoginFragment: getAuthToken(): Success")
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }

                    is Resource.Loading -> {
                        Log.d(DEBUG_TAG, "LoginFragment: getAuthToken(): Loading")
                    }

                    is Resource.Failure -> {
                        Log.d(DEBUG_TAG, "LoginFragment: getAuthToken(): Failure")
                        response.message?.let {
                            Log.d(DEBUG_TAG, "An occur happened: $it")
                            Snackbar.make(
                                requireContext(),
                                binding.root,
                                "An occur happened: $it",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}