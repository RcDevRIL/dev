import smtplib

print('####### ENVOI DE MAIL #######\n')
smtp_host = ''
smtp_port = 0000
smtp_cli = smtplib.SMTP(smtp_host, smtp_port)
smtp_from = 'romain.chevallier@viacesi.fr'
smtp_to = 'romain.chevallier@viacesi.fr'
mail_content = 'Contenu du mail'
smtp_cli.sendmail(smtp_from, smtp_to, mail_content)
