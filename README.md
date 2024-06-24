# Zulip App

#### Общие требования:

- Данная работа является проверкой всех знаний, полученных на курсе.
- Рекомендуется выбирать средства/подходы/инструменты, о которых говорилось на лекциях/семинарах. Использование других средств не запрещено, однако их следует выбирать исходя из здравого смысла и не злоупотреблять ими(например, если нужно показать диалог, лучше пользоваться средствами, предоставленными Android Sdk/Support Library, а не тащить какую-то библиотеку с навороченными диалогами.)
- Прежде всего оценивается работоспособность (реализация всех функций, описанных в ТЗ) и качество кода (архитектура, читаемость, организация кода) приложения. Далее по приоритету - UI/анимация.
- Поощряется стандартный подход к дизайну: material design. Это сэкономит время т.к. многие компоненты material design реализованы в support библиотеках, а иконки для большинства случаев можно импортировать прямо в AndroidStudio
- Моменты не оговоренные в ТЗ могут быть реализованы по своему усмотрению

#### Особо стоит обратить внимание на:
1
- **Ресурсы**: все строки, используемые в нескольких местах размеры, цвета, и т.п. должны находиться в ресурсах
- **Дублирование кода** (в том числе и в xml-ках с версткой): оно должно быть сведено к минимуму, если вы копируете кусок кода в другое место - это повод задуматься о выносе в переиспользуемый код. В java/kotlin у вас есть интерфейсы, классы, композиция и декорирование (наследование старайтесь не использовать). А для xml можно использовать include, чтобы переиспользовать уже имеющийся layout.
- **Большие классы**: старайтесь не раздувать класс до 100+ строк кода, их становится сложно читать, и скорее всего в этом случае он делает слишком много вещей (single responsibility и все такое).
- **Комментарии**: вместо многочисленных комментариев, старайтесь разбивать код на классы методы с осмысленным именованием, чаще всего это может заменить комментарий. Это избавит вас от надобности поддерживать комментарии в актуальном состоянии при изменении/рефакторинге кода.
- **Следите за утечками**: можно периодически (например после каждого реализованного экрана) включать leak canary и проходиться по экранам.

### Порядок выполнения домашних заданий

* [Делаете форк](https://gitlab.com/Gushel_divl/tfs_spring_2024/-/forks/new) от главного проекта
* В настройках репозитория (Project information -> Members) предоставляете доступ к проекту Вашему ментору(его ник можете спросить у него самого в личке, контакты у вас есть). Роль - developer.
- Если еще не ставили, то устанавливаете git и [настраиваете ssh](https://docs.gitlab.com/ee/user/ssh.html) или пользуемся функционалом AndroidStudio для работы с Git.
- Клонируете форкнутый репозиторий к себе на комп (git clone "адрес репозитория"), адрес репозитория можно посмотреть по нажатию на кнопку "Clone" на странице вашего проекта на гитлабе
- Материалы к каждой дом.работе находятся в отдельных папках (например, HomeWork_1). В папке только описание. Для первой домашки неоходимо самостоятельно создать проект в папке. Начиная со второй начнётся наша курсовая и необходимо создать отдельную папку под него и там разместить свой проект.
- Чтобы для каждой дом. работы не делать форк, нужно каждую домашку делать в отдельной ветке
- После выполнения задания, заходите в "Ваш форк на гитлабе"->"Merge
  Requests"->"New merge request" и в качестве Source branch выбираете
  ветку, в которой выполнено задание, в Target branch должна быть ветка
  master(мастер для первых двух, в дальнейшем это будет HomeWork_3->HomeWork_2 и так далее). Реквест нужно делать в ВАШ репозиторий с форком (а не в общий, от которого был сделан форк), т.е. source project и target project должны быть ВАШИМ форком.
- После того, как ваш merge-request был проверен(ментор поставил лайк или approve), необходимо влить его в основную ветку (каскадно до master).
- Если предыдущий merge-request не был проверен (например, hw_1 -> master), то в качестве target branch необходимо выбрать ветку с предыдущей домашки (hw_2 -> hw_1)
