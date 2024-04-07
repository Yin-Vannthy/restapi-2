create database restapi;

create table attendees
(
    attendee_id   serial primary key,
    attendee_name varchar(100) not null,
    email         varchar(100) not null
);

create table venues
(
    venue_id   serial primary key,
    venue_name varchar(100) not null,
    location   varchar(100) not null
);

create table events
(
    event_id   serial primary key,
    event_name varchar(100) not null,
    event_date DATE         not null,
    venue_id   int          not null references venues (venue_id) on delete cascade
);

create table event_attendee
(
    id          serial primary key,
    event_id    int not null references events (event_id) on delete cascade,
    attendee_id int not null references attendees (attendee_id) on delete cascade
);

