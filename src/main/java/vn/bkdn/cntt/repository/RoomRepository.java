package vn.bkdn.cntt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.bkdn.cntt.entity.Room;

import java.util.List;

/**
 * Created by Tri on 2/26/2017.
 */

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAll();
}
