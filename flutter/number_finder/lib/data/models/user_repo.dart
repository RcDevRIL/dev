import 'package:flutter/material.dart';

class UserRepo with ChangeNotifier {
  String token;
  String nickname;

  String get getToken => token ??= '0';
  String get getNickname => nickname ??= 'dupont';

  UserRepo({
    Key key,
    @required this.nickname,
    this.token,
  });
}
