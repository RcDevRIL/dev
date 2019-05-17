import 'dart:async';

import 'package:login_learn_bloc3/bloc_helpers/bloc_provider.dart';
import 'package:login_learn_bloc3/validators/validators.dart';
import 'package:rxdart/rxdart.dart';

class LoginFormBloc extends Object with EmailValidator, PasswordValidator implements BlocBase {

  final BehaviorSubject<String> _emailController = BehaviorSubject<String>();
  final BehaviorSubject<String> _passwordController = BehaviorSubject<String>();

  // INPUTS
  Function(String) get onEmailChanged => _emailController.sink.add;
  Function(String) get onPasswordChanged => _passwordController.sink.add;
  // VALIDATORS
  Stream<String> get emailValidation => _emailController.stream.transform(validateEmail);
  Stream<String> get passwordValidation => _passwordController.stream.transform(validatePassword);
  // OUTPUTS
  Stream<bool> get login => Observable.combineLatest2(emailValidation, passwordValidation, (e,p)=>true);

  @override
  void dispose() {
    _emailController?.close();
    _passwordController?.close();
  }
}