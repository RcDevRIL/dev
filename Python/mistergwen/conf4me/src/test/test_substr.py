from ..main.string_extensions import substr
import pytest


def test_substr():
    """This is a test to see if this str wrapper 
    actually **raises** something. 
    """
    with pytest.raises(IndexError):
        substr('test', 1, 5)
    assert substr('test', 0, 'test'.__len__() - 1) != substr(
        'test', 1, 'test'.__len__() - 1)
    assert substr('test', 1, 2) == 'es'
    assert substr('test', 1, 1) == 'e'
