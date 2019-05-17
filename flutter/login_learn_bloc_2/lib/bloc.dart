import 'dart:async';
import 'validators.dart';
import 'package:rxdart/rxdart.dart';

class LoginBloc extends Object with Validators implements BaseBloc {
  final _emailController = BehaviorSubject<String>();
  final _passwordController = BehaviorSubject<String>();


  StreamSink<String> get emailChanged => _emailController.sink;
  StreamSink<String> get passwordChanged => _passwordController.sink;


  Observable<String> get emailStream =>
      _emailController.stream.transform(emailValidator);
  Observable<String> get passwordStream =>
      _passwordController.stream.transform(passwordValidator);

  Observable<bool> get submitCheck => Observable.combineLatest2(emailStream,
      passwordStream, (e, p) => true);
  //submit('e');



  Stream<bool> submit(String test) {
  emailStream;
  passwordStream;
    print(test);
    if('ad@.'==_emailController.stream.value){
      print('enorme');
      return Observable.just(true);
    }
    else return Stream<bool>.empty();
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
