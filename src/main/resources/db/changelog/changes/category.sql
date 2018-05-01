create table questions.category (
  id bigserial primary key,
  name varchar(250) not null unique,
  description text,
  user_id bigint
);

create table questions.question (
  id bigserial primary key,
  question text not null,
  answer text not null,
  category_id bigint not null references questions.category(id) ON DELETE CASCADE
);
