import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'package:logger/logger.dart';
import 'dart:convert';

class GameAPI with ChangeNotifier {
  final log = Logger();

  final String baseUrl = 'https://numberfinder.neodigits.fr';

  Future<Response> startGame(String nickName) async {
    var jsonResponse;
    var nickNameUTF8 = utf8.encode(nickName);
    try {
      jsonResponse = await http.post(
        baseUrl + '/startGame',
        headers: {'Content-type': 'application/json'},
        body: jsonEncode({'pseudo': nickNameUTF8}),
      );
    } catch (e) {
      log.e("Error when trying to connect:\n" + e.toString());
    }
    if (jsonResponse?.statusCode == 200 && jsonResponse.body.isNotEmpty()) {
      return jsonResponse;
    } else {
      return Response(
        'Server unreachable',
        400,
      );
    }
  }
}
