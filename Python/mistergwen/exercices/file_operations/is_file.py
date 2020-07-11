from os.path import isfile


# Wrapper over os.path.isfile() so we return a sentence instead a boolean
def file_exists(path) -> str:
    try:
        assert isfile(path)
        return f'File '{path}' exists'
    except AssertionError:
        return f'File '{path}' does not exist (or is a directory :thinking:)'


# Will print 'File exists'
print(file_exists('./issue_#3.py'))
# Will print 'File does not exist'
print(file_exists('./issue_#12.py'))
