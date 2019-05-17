import 'package:login_learn_bloc3/bloc_helpers/bloc_provider.dart';
import 'package:login_learn_bloc3/blocs/authentication/authentication_bloc.dart';
import 'package:login_learn_bloc3/pages/decision_page.dart';
import 'package:login_learn_bloc3/pages/initialization_page.dart';
import 'package:login_learn_bloc3/pages/registration_page.dart';
import 'package:flutter/material.dart';

class Application extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return BlocProvider<AuthenticationBloc>(
      bloc: AuthenticationBloc(),
        child: MaterialApp(
          title: 'BLoC Samples',
          theme: ThemeData(
            primarySwatch: Colors.blue,
          ),
          routes: {
            '/decision': (BuildContext context) => DecisionPage(),
            '/register': (BuildContext context) => RegistrationPage(),
          },
          home: InitializationPage(),
        ),

    );
  }
}
