import 'package:login_learn_bloc3/bloc_helpers/bloc_provider.dart';
import 'package:login_learn_bloc3/bloc_widgets/bloc_state_builder.dart';
import 'package:login_learn_bloc3/blocs/authentication/authentication_bloc.dart';
import 'package:login_learn_bloc3/blocs/authentication/authentication_event.dart';
import 'package:login_learn_bloc3/blocs/authentication/authentication_state.dart';
import 'package:login_learn_bloc3/blocs/authentication/login_form_bloc.dart';
import 'package:login_learn_bloc3/widgets/pending_action.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class AuthenticationPage extends StatefulWidget {
  @override
  AuthenticationPageState createState() => AuthenticationPageState();
}

class AuthenticationPageState extends State<AuthenticationPage> {
  TextEditingController _emailController;
  TextEditingController _passwordController;
  LoginFormBloc _loginFormBloc;
  ///
  /// Prevents the use of the "back" button
  ///
  Future<bool> _onWillPopScope() async {
    return false;
  }

  @override
  void initState() {
    super.initState();
    _emailController = TextEditingController();
    _passwordController = TextEditingController();
    _loginFormBloc = LoginFormBloc();
  }

  @override
  void dispose() {
    super.dispose();
    _emailController?.dispose();
    _passwordController?.dispose();
    _loginFormBloc?.dispose();
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setPreferredOrientations(
        [DeviceOrientation.portraitDown, DeviceOrientation.portraitUp]);
    AuthenticationBloc bloc = BlocProvider.of<AuthenticationBloc>(context);
    return WillPopScope(
      onWillPop: _onWillPopScope,
      child: SafeArea(
        child: Scaffold(
          appBar: AppBar(
            title: Text('Authentication Page'),
            leading: Container(),
          ),
          body: Container(
            child:
                BlocEventStateBuilder<AuthenticationState>(
              bloc: bloc,
              builder: (BuildContext context, AuthenticationState state) {
                if (state.isAuthenticating) {
                  return PendingAction();
                }

                if (state.isAuthenticated){
                  return Container(
                    color: Colors.black87,
                    child: Center(child: Icon(Icons.check_circle, color: Colors.cyan, size: 50.0,))
                  );
                }

                List<Widget> children = <Widget>[];

                children.add(
                    StreamBuilder<Object>(
                      stream: _loginFormBloc.emailValidation,
                      builder: (context, snapshot) {
                        return Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: TextField(
                            onChanged: _loginFormBloc.onEmailChanged,
                            controller: _emailController,
                            keyboardType: TextInputType.emailAddress,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: "Enter email",
                              labelText: "Email",
                              errorText: snapshot.error,
                            ),
                          ),
                        );
                      }
                    )
                );

                children.add(
                    StreamBuilder<Object>(
                      stream: _loginFormBloc.passwordValidation,
                      builder: (context, snapshot) {
                        return Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: TextField(
                            onChanged: _loginFormBloc.onPasswordChanged,
                            controller: _passwordController,
                            keyboardType: TextInputType.text,
                            obscureText: true,
                            decoration: InputDecoration(
                              border: OutlineInputBorder(),
                              hintText: "Enter password",
                              labelText: "Password",
                              errorText: snapshot.error,
                            ),
                          ),
                        );
                      }
                    )
                );

                children.add(
                    StreamBuilder<bool>(
                        stream: _loginFormBloc.login,
                        builder: (context, snapshot) {
                          return Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: RaisedButton(
                              child: Text('Login'),
                              onPressed: (snapshot.hasData && snapshot.data == true)
                              ? () {
                                bloc.emitEvent(AuthenticationEventLogin(
                                      event: LoginEventType.working,
                                      name: _emailController.text,
                                      password: _passwordController.text));
                                }
                              : null,
                          ));
                        }
                    )
                );
                // Display a text if the authentication failed
                if (state.hasFailed){
                  children.add(
                    Text('Authentication failure!'),
                  );
                }

                
                return SingleChildScrollView(
                  child: Column(
                    children: children,
                  ),
                );
              },
            ),
          ),
        ),
      ),
    );
  }
}


//                // Button to fake the authentication (success)
//                children.add(
//                  ListTile(
//                      title: RaisedButton(
//                        child: Text('Log in (success)'),
//                        onPressed: () {
//                            bloc.emitEvent(AuthenticationEventLogin(name: 'Didier'));
//                        },
//                      ),
//                    ),
//                );

//                // Button to fake the authentication (failure)
//                children.add(
//                  ListTile(
//                      title: RaisedButton(
//                        child: Text('Log in (failure)'),
//                        onPressed: () {
//                            bloc.emitEvent(AuthenticationEventLogin(name: 'failure'));
//                        },
//                      ),
//                    ),
//                );

//                // Button to redirect to the registration page
//                children.add(
//                  ListTile(
//                    title: RaisedButton(
//                      child: Text('Register'),
//                      onPressed: () {
//                        Navigator.of(context)
//                            .pushNamed('/register');
//                      },
//                    ),
//                  ),
//                );