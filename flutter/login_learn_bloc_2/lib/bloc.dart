import 'dart:async';
import 'validators.dart';
import 'package:rxdart/rxdart.dart';

class LoginBloc extends Object with Validators implements BaseBloc {
  final _emailController = BehaviorSubject<String>();
  final _passwordController = BehaviorSubject<String>();
  StreamSink<String> get emailChanged => _emailController.sink;
  StreamSink<String> get passwordChanged => _passwordController.sink;

  Stream<String> get emailStream =>
      _emailController.stream.transform(emailValidator);
  Stream<String> get passwordStream =>
      _passwordController.stream.transform(passwordValidator);

  Stream<bool> get submitCheck => Observable.combineLatest2(emailStream,
      passwordStream, (String e, String p) => submit(p.toString()));
  bool submit(String test) {
    print(test);
    return true;
  }

  @override
  void dispose() {
    _emailController?.close();
    _passwordController?.close();
  }
}

abstract class BaseBloc {
  void dispose();
}
