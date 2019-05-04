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

      final token = await userRepository.authenticate(
        username: event.username,
      );
      if ('token' == token) {
        authenticationBloc.dispatch(LoggedIn(token: token));
        yield LoginInitial();
      } else
        yield LoginFailure(error: token.toString());
    }
    if (event is OnFormUpdate) {
      if (event.props[1] == 'admin')
        yield LoginChanged();
      else
        yield LoginFailure(error: 'Wrong id');
    }
  }
}
