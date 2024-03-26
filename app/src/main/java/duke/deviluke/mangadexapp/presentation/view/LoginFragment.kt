package duke.deviluke.mangadexapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import duke.deviluke.mangadexapp.BuildConfig
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.R
import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.toModel
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

    private fun getAuthToken(authData: AuthData) {
        viewModel.getAuthToken(authData)
        viewModel.authToken.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    Log.d(DEBUG_TAG, "Success")
                    response.data?.let {
                        val token = it.toModel()
                        Log.d(
                            DEBUG_TAG,
                            "accessToken: ${token.accessToken}, refreshToken: ${token.refreshToken}"
                        )
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                    }
                }

                is Resource.Loading -> {
                    Log.d(DEBUG_TAG, "Loading")
                }

                is Resource.Failure -> {
                    Log.d(DEBUG_TAG, "Failure")
                    response.message?.let {
                        Snackbar.make(
                            requireContext(),
                            binding.root,
                            "An occur happened: $it",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}