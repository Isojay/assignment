package com.assignment.Repository;

import com.assignment.Entity.Packet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacketRepository extends JpaRepository<Packet, String> {
}
