update announcement
set owner_user_id = 2
where a_id between 1 and 18;

update announcement
set owner_user_id = 1
where a_id in (19, 20);