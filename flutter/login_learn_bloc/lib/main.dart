import 'package:flutter/material.dart';

import 'package:bloc/bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:login_learn_bloc/data/model/user-repo.dart';

import 'package:login_learn_bloc/data/blocs/bloc.dart';
import 'package:login_learn_bloc/ui/elements/loading-indicator.dart';
import 'package:login_learn_bloc/ui/home-page.dart';
import 'package:login_learn_bloc/ui/login-page.dart';
import 'package:login_learn_bloc/ui/splash-page.dart';

class SimpleBlocDelegate extends BlocDelegate {
  @override
  void onTransition(Transition t) {
    print(t.toString());
  }
}

void main() {
  BlocSupervisor().delegate = SimpleBlocDelegate();
  runApp(AppStart(userRepository: UserRepository()));
}

class AppStart extends StatefulWidget {
  final UserRepository userRepository;

  AppStart({Key key, @required this.userRepository}) : super(key: key);

  @override
  _AppState createState() => _AppState();
}

class _AppState extends State<AppStart> {
  AuthenticationBloc authBloc;
  UserRepository get userRepository => widget.userRepository;

  @override
  void initState() {
    authBloc = AuthenticationBloc(userRepository: userRepository);
    authBloc.dispatch(AppStarted());
    super.initState();
  }

  @override
  void dispose() {
    authBloc.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider<AuthenticationBloc>(
      bloc: authBloc,
      child: MaterialApp(
        home: BlocBuilder<AuthenticationEvent, AuthenticationState>(
          bloc: authBloc,
          builder: (context, AuthenticationState state) {
            if (state is AuthenticationUninitialized) {
              return SplashPage();
            }
            if (state is AuthenticationAuthenticated) {
              return HomePage();
            }
            if (state is AuthenticationUnauthenticated) {
              return LoginPage(
                userRepository: userRepository,
              );
            }
            if (state is AuthenticationLoading) {
              return LoadingIndicator();
            }
          },
        ),
      ),
    );
  }
}
