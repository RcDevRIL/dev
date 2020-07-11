def substr(my_str, start, end) -> str:
    """Implement a custom substring() method.
    Somehow an extension on the `str` class (like in Dart).
    I did it because I couldn't find it on docs available on IDE (no internet searches for this)
    """
    substr = ''
    for i in range(start, end + 1):
        substr += my_str[i]
    return substr
