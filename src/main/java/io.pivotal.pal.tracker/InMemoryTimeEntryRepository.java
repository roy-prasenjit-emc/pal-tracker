package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> repo;
    private long count;

    public InMemoryTimeEntryRepository() {
        repo = new HashMap<>();
        count = 0;
    }

    @Override

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry entry = find(timeEntry.getId());
        if (entry == null) {
            long id = ++count;
            timeEntry.setId(id);
            repo.put(id, timeEntry);
            entry = find(id);
        }
        return entry;
    }

    @Override
    public TimeEntry find(long id) {
        return repo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(repo.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry entry = find(id);
        if (entry == null) return entry;
        timeEntry.setId(id);
        repo.put(id, timeEntry);
        return find(id);
    }

    @Override
    public void delete(long id) {
        repo.remove(id);
    }
}
