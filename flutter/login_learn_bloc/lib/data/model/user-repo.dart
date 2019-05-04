import 'package:meta/meta.dart';

class UserRepository {
  final Map<String, String> users;

  UserRepository(this.users);

  Future<String> authenticate({
    @required String username,
    // @required String password,
  }) async {
    await Future.delayed(Duration(seconds: 1));
    if (users["hasToken"].contains("yes")) {
      return 'token';
    } else {
      return 'false';
    }
  }

  Future<void> deleteToken() async {
    /// delete from keystore/keychain
    await Future.delayed(Duration(seconds: 1));
    return;
  }

  Future<void> persistToken(String token) async {
    /// write to keystore/keychain
    await Future.delayed(Duration(seconds: 1));
    return;
  }

  Future<bool> hasToken() async {
    /// read from keystore/keychain
    await Future.delayed(Duration(seconds: 1));
    return false;
  }
}
