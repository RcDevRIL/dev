import 'package:meta/meta.dart';
import 'package:equatable/equatable.dart';

abstract class LoginEvent extends Equatable {
  LoginEvent([List props = const []]) : super(props);
}

class LoginButtonPressed extends LoginEvent {
  final String username;
  final String password;

  LoginButtonPressed({
    @required this.username,
    @required this.password,
  }) : super([username, password]);

  @override
  String toString() =>
      'LoginButtonPressed { username: $username, password: $password }';
}

class OnFormUpdate extends LoginEvent {
  final String userField;
  final String pwField;

  OnFormUpdate({
    @required this.userField,
    @required this.pwField,
  }) : super([userField, pwField]);

  @override
  String toString() {
    return 'OnFormUpdate { userField: $userField, pwField: $pwField }';
  }
}
