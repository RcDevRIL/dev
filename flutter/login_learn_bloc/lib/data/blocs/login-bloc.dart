import 'dart:async';

import 'package:login_learn_bloc/data/blocs/auth-bloc.dart'
    show AuthenticationBloc;
import 'package:login_learn_bloc/data/blocs/auth-event.dart';
import 'package:login_learn_bloc/data/blocs/login-event.dart';
import 'package:login_learn_bloc/data/blocs/login-state.dart';
import 'package:login_learn_bloc/data/model/user-repo.dart';
import 'package:meta/meta.dart';
import 'package:bloc/bloc.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  final UserRepository userRepository;
  final AuthenticationBloc authenticationBloc;

  LoginBloc({
    @required this.userRepository,
    @required this.authenticationBloc,
  })  : assert(userRepository != null),
        assert(authenticationBloc != null);

  @override
  LoginState get initialState => LoginInitial();

  @override
  Stream<LoginState> mapEventToState(
    LoginState currentState,
    LoginEvent event,
  ) async* {
    if (event is LoginButtonPressed) {
      yield LoginLoading();

      try {
        final token = await userRepository.authenticate(
          username: event.username,
          password: event.password,
        );

        authenticationBloc.dispatch(LoggedIn(token: token));
        yield LoginInitial();
      } catch (error) {
        yield LoginFailure(error: error.toString());
      }
    }
  }
}
