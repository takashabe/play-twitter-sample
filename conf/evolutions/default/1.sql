# --- !Ups
create sequence tweet_id_seq;
create table tweet (
    id integer not null default nextval('tweet_id_seq'),
    content varchar(140) not null,
    created_at timestamp not null
);

# --- !Downs
drop table tweet;
drop sequence tweet_id_seq;