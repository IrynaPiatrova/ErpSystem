# Thesis project 
name: ErpSystem 
1. на главной странице у админа инициализируются запросы на ...(отпуск, болезни,..) и запросы на выполненные тикеты, и последний коммент каждого тикета 
2. пользователи при изменении статуса (opened, in progress, paused, ready for testing, finished)
3. на главной странице пользователя отображаются все действувющие тикеты + оставшееся время до дедлайна
4. у пользователя может быть одновременно только один тикет in progress, но в целом несколько незаверешенных, когда ему назначают еще один тикет статусом in progress то действуюший тикет становится на паузу. 
5. Шифрование для паролей +/-
7. Валидатор для телефона
9. при добавлении работника и профиля нужно сделать чтобы при выборе конкретного отдела список должностей доступных изменялся
12. в качестве почты можно использовать БД и на ссылке на почту посылаем сооющение в БД, а у адресата инициализируется сообщения из БД
14. при добавлении профиля сделать ключевое слово для восстановления пароля, и при редактировании профиля ответна это слово чтобы поменять пароль, также нужно добавить в скрипт в таблицу профиля два поля: key_word и answer_on_key_word например(в них это будет храниться)
19. сделать enum для статусов, для должности, подразделения
20. завершением админом тикета со статуса ready for testing в статус finished
21. админ подтверждает запрос на болезнь/отпуск переходя на страницу редактирования профиля (там изменение статуса)+одновременная запись в таблицу time_vocation
# Worklog
