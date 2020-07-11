from src.main.string_extensions import substr


def template_conf(template, args) -> str:
    """Use of **args method to format a template.
    /!\ this method breaks when you parse a template with '{' chars because of **args pattern, and you will need to find a workaround /!\
    /!\ this method breaks when you don't push all variables defined in `template`
    """
    return template.format(**args)


def gen_file(user, **kwargs) -> str:
    """This method generate a copy of `default_nginx.conf` content and replace by kwargs value **or a default one**.
    This idea of default make it slightly more robust than other method in terms of raised exceptions but is hardly maintainable/readable.
    """
    with open('data/default_nginx.conf', 'r') as f_content:
        default_conf = f_content.read()
    result = ''
    for line in default_conf.splitlines():
        if(line.lstrip().startswith('user')):
            result += line.replace(
                substr(line, line.index('user') + 'user'.__len__() + 1, line.index(';') - 1), f'    {user}')
        elif(line.lstrip().startswith('worker_processes')):
            worker_processes = kwargs.get('worker_processes', 5)
            result += line.replace(
                substr(line, line.index('worker_processes') + 'tcp_nopush'.__len__() + 1, line.index(';') - 1), f'    {worker_processes}')
        elif(line.lstrip().startswith('error_log')):
            error_log = kwargs.get('error_log', 'logs/error.log')
            result += line.replace(
                substr(line, line.index('error_log') + 'error_log'.__len__() + 1, line.index(';') - 1), f'    {error_log}')
        elif(line.lstrip().startswith('pid')):
            pid = kwargs.get('pid', 'logs/nginx.pid')
            result += line.replace(
                substr(line, line.index('pid') + 'pid'.__len__() + 1, line.index(';') - 1), f'    {pid}')
        elif(line.lstrip().startswith('worker_rlimit_nofile')):
            worker_rlimit_nofile = kwargs.get('worker_rlimit_nofile', 8192)
            result += line.replace(
                substr(line, line.index('worker_rlimit_nofile') + 'worker_rlimit_nofile'.__len__() + 1, line.index(';') - 1), f'    {worker_rlimit_nofile}')
        elif(line.lstrip().startswith('worker_connections')):
            worker_connections = kwargs.get('worker_connections', 4096)
            result += line.replace(
                substr(line, line.index('worker_connections') + 'worker_connections'.__len__() + 1, line.index(';') - 1), f'    {worker_connections}')
        elif(line.lstrip().startswith('default_type')):
            default_type = kwargs.get(
                'default_type', 'application/octet-stream')
            result += line.replace(
                substr(line, line.index('default_type') + 'default_type'.__len__() + 1, line.index(';') - 1), f'    {default_type}')
        elif(line.lstrip().startswith('access_log')):
            access_log = kwargs.get('access_log', 'logs/access.log  main')
            result += line.replace(
                substr(line, line.index('access_log') + 'access_log'.__len__() + 1, line.index(';') - 1), f'    {access_log}')
        elif(line.lstrip().startswith('sendfile')):
            sendfile = kwargs.get('sendfile', 'on')
            result += line.replace(
                substr(line, line.index('sendfile') + 'sendfile'.__len__() + 1, line.index(';') - 1), f'    {sendfile}')
        elif(line.lstrip().startswith('tcp_nopush')):
            tcp_nopush = kwargs.get('tcp_nopush', 'on')
            result += line.replace(
                substr(line, line.index('tcp_nopush') + 'tcp_nopush'.__len__() + 1, line.index(';') - 1), f'    {tcp_nopush}')
        elif(line.lstrip().startswith('server_names_hash_bucket_size')):
            server_names_hash_bucket_size = kwargs.get(
                'server_names_hash_bucket_size', 128)
            result += line.replace(
                substr(line, line.index('server_names_hash_bucket_size') + 'server_names_hash_bucket_size'.__len__() + 1, line.index(';') - 1), f'    {server_names_hash_bucket_size}')
        else:
            result += line
        result += '\r\n'
    return result
